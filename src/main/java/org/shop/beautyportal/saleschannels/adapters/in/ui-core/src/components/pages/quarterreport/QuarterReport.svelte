
<script>
    import Section from "../../organisms/section/Section.svelte";
    import Table from "../../atoms/table/Table.svelte";
    import Toolbar from "../../molecules/toolbar/Toolbar.svelte";
    import Button from "../../atoms/button/Button.svelte";
    import Input from "../../atoms/input/Input.svelte";
    import Select from "../../atoms/select/Select.svelte";

    const MONTHS = ["Styczeń","Luty","Marzec","Kwiecień","Maj","Czerwiec","Lipiec","Sierpień","Wrzesień","Październik","Listopad","Grudzień"];
    const Q = { 1:[1,2,3], 2:[4,5,6], 3:[7,8,9], 4:[10,11,12] };

    export let distributorId = crypto.randomUUID?.() ?? "";

    let year = new Date().getFullYear();
    let quarter = Math.ceil((new Date().getMonth()+1)/3);

    const newRow = (m) => ({ month:m, professional:0, pharmacy:0, b2c:0, b2b:0, third:0, other:0, newClients:0, currency:"PLN" });
    let rows = Q[quarter].map(newRow);
    $: rows = Q[Number(quarter)].map(m => rows.find(r=>r.month===m) || newRow(m));

    let eurRates = {};    // { [month]: plnPerEur }
    let loadingRates = false;
    let saving = false;

    function sumRow(r){ return (+r.professional)+(+r.pharmacy)+(+r.b2c)+(+r.b2b)+(+r.third)+(+r.other); }
    function fmt(n){ return Number(n||0).toLocaleString(undefined,{ maximumFractionDigits:2 }); }

    async function loadRates(){
        loadingRates = true;
        try{
            const y = Number(year);
            const months = Q[Number(quarter)];
            const fetchRate = async (yy, m) => {
                const yyyymmdd = (d)=>new Date(Date.UTC(yy, m-1, d)).toISOString().slice(0,10);
                const first = yyyymmdd(1);
                const last  = yyyymmdd(new Date(yy, m, 0).getUTCDate());
                const url = `https://api.nbp.pl/api/exchangerates/rates/A/EUR/${first}/${last}/?format=json`;
                const r = await fetch(url, { headers:{ Accept:"application/json" } });
                if(!r.ok) return null;
                const j = await r.json();
                const arr = (j?.rates || []).map(x=>x.mid);
                if(!arr.length) return null;
                return arr.reduce((a,b)=>a+b,0)/arr.length;
            };
            const pairs = await Promise.all(months.map(async m => [m, await fetchRate(y, m)]));
            eurRates = Object.fromEntries(pairs.filter(([,v]) => v));
            alert("Pobrano kursy NBP dla miesięcy kwartału.");
        }catch(e){
            alert("Błąd pobierania kursów NBP.");
        }
        loadingRates = false;
    }

    async function save(){
        saving = true;
        try{
            const payload = {
                distributorId,
                year: Number(year),
                quarter: Number(quarter),
                rows: rows.map(r => ({
                    month: r.month,
                    values: {
                        professional: Number(r.professional),
                        pharmacy: Number(r.pharmacy),
                        ecommerceB2C: Number(r.b2c),
                        ecommerceB2B: Number(r.b2b),
                        thirdParty: Number(r.third),
                        other: Number(r.other),
                        newClients: Number(r.newClients),
                        currency: r.currency
                    }
                }))
            };
            const res = await fetch("/api/v1/sales-channels/quarter-reports", {
                method:"POST",
                headers:{ "Content-Type":"application/json" },
                body: JSON.stringify(payload)
            });
            if(!res.ok) throw new Error("save failed");
            const j = await res.json();
            alert("Raport utworzony: " + j.id);
        }catch(e){
            alert("Błąd zapisu raportu.");
        }
        saving = false;
    }
</script>

<style lang="scss">
  @import "quarterreport.scss";
  @import "../../atoms/table/table.scss";
</style>

<Section title="Raport kwartalny" description="Wartości per kanał z auto-sumą i przeliczeniem EUR (NBP)">
    <div slot="toolbar" class="quarter__controls">
        <div>
            <label class="label">Rok</label>
            <Input variant="primary" type="number" bind:value={year} />
        </div>
        <div>
            <label class="label">Kwartał</label>
            <Select variant="primary" bind:value={quarter} items={[1,2,3,4]} />
        </div>
    </div>

    <div class="quarter__toolbar">
        <Toolbar separated>
            <div slot="start" style="display:flex;gap:.5rem;align-items:center;">
                <Button variant="ghost" disabled={loadingRates} on:click={loadRates}>
                    {#if loadingRates}Ładowanie...{:else}Pobierz kursy NBP{/if}
                </Button>
            </div>
            <div slot="end">
                <Button variant="primary" disabled={saving} on:click={save}>
                    {#if saving}Zapisywanie...{:else}Zapisz{/if}
                </Button>
            </div>
        </Toolbar>
    </div>

    <div class="quarter__table">
        <Table variant="compact" caption="Kolumna EUR liczona z kursu NBP, jeśli waluta = PLN.">
            <svelte:fragment slot="head">
                <tr>
                    <th>Miesiąc</th>
                    <th>Professional</th>
                    <th>Pharmacy</th>
                    <th>E-commerce B2C</th>
                    <th>E-commerce B2B</th>
                    <th>Third party</th>
                    <th>Other</th>
                    <th>New clients</th>
                    <th>Waluta</th>
                    <th>Suma</th>
                    <th>Suma (EUR)</th>
                </tr>
            </svelte:fragment>

            {#each rows as r (r.month)}
                {@const total = sumRow(r)}
                {@const rate = eurRates[r.month]}
                {@const eur = rate && r.currency === "PLN" ? total / rate : null}
                <tr>
                    <td>{MONTHS[r.month-1]}</td>
                    <td><Input variant="primary" num type="number" step="0.01" bind:value={r.professional} /></td>
                    <td><Input variant="primary" num type="number" step="0.01" bind:value={r.pharmacy} /></td>
                    <td><Input variant="primary" num type="number" step="0.01" bind:value={r.b2c} /></td>
                    <td><Input variant="primary" num type="number" step="0.01" bind:value={r.b2b} /></td>
                    <td><Input variant="primary" num type="number" step="0.01" bind:value={r.third} /></td>
                    <td><Input variant="primary" num type="number" step="0.01" bind:value={r.other} /></td>
                    <td><Input variant="secondary" type="number" step="1" bind:value={r.newClients} /></td>
                    <td><Select variant="secondary" bind:value={r.currency} items={["PLN","EUR","USD"]} /></td>
                    <td class="cell--num">{fmt(total)} {r.currency}</td>
                    <td class="cell--num">{eur != null ? `${fmt(eur)} EUR` : "–"}</td>
                </tr>
            {/each}

            <svelte:fragment slot="foot">
                <tr>
                    <td>RAZEM</td>
                    {#each ['professional','pharmacy','b2c','b2b','third','other'] as k}
                        <td class="cell--num">
                            {fmt(rows.reduce((s,x)=> s + Number(x[k] || 0), 0))}
                        </td>
                    {/each}
                    <td class="cell--num">{rows.reduce((s,x)=> s + Number(x.newClients || 0), 0)}</td>
                    <td>—</td>
                    <td class="cell--num">
                        {fmt(rows.reduce((s,x)=> s + sumRow(x), 0))} PLN
                    </td>
                </tr>
            </svelte:fragment>
        </Table>
    </div>
</Section>
