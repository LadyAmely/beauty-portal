
<script>
    import Layout from "../../templates/layout/Layout.svelte";
    import Section from "../../organisms/section/Section.svelte";
    import Table from "../../atoms/table/Table.svelte";
    import Toolbar from "../../molecules/toolbar/Toolbar.svelte";

    const MONTHS = ["Styczeń","Luty","Marzec","Kwiecień","Maj","Czerwiec","Lipiec","Sierpień","Wrzesień","Październik","Listopad","Grudzień"];
    const Q = { 1:[1,2,3], 2:[4,5,6], 3:[7,8,9], 4:[10,11,12] };

    export let distributorId = crypto.randomUUID?.() ?? "";

    let year = new Date().getFullYear();
    let quarter = Math.ceil((new Date().getMonth()+1)/3);

    const newRow = (m) => ({ month:m, professional:0, pharmacy:0, b2c:0, b2b:0, third:0, other:0, newClients:0, currency:"PLN" });
    let rows = Q[quarter].map(newRow);
    $: rows = Q[Number(quarter)].map(m => rows.find(r=>r.month===m) || newRow(m));

    let eurRates = {};
    let loadingRates = false;
    let saving = false;

    const toNum = (v) => (v === '' || v == null ? 0 : +v);
    function sumRow(r){ return toNum(r.professional)+toNum(r.pharmacy)+toNum(r.b2c)+toNum(r.b2b)+toNum(r.third)+toNum(r.other); }
    function fmt(n){ return Number(n||0).toLocaleString(undefined,{ maximumFractionDigits: 2 }); }

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
        }catch{ alert("Błąd pobierania kursów NBP."); }
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
                        professional: toNum(r.professional),
                        pharmacy: toNum(r.pharmacy),
                        ecommerceB2C: toNum(r.b2c),
                        ecommerceB2B: toNum(r.b2b),
                        thirdParty: toNum(r.third),
                        other: toNum(r.other),
                        newClients: toNum(r.newClients),
                        currency: r.currency
                    }
                }))
            };
            const res = await fetch("/api/v1/sales-channels/quarter-reports", {
                method:"POST", headers:{ "Content-Type":"application/json" }, body: JSON.stringify(payload)
            });
            if(!res.ok) throw new Error();
            const j = await res.json();
            alert("Raport utworzony: " + j.id);
        }catch{ alert("Błąd zapisu raportu."); }
        saving = false;
    }
</script>

<style lang="scss">
  @import "quarterreport.scss";
  @import "../../atoms/table/table.scss";
  @import "../../atoms/input/input.scss";
  @import "../../atoms/select/select.scss";
</style>

<Layout sidebar={false} maxWidth={1100}>
    <div slot="header"><strong>Sales Channels</strong></div>

    <Section title="Raport kwartalny" description="Wartości per kanał z auto-sumą i przeliczeniem EUR (NBP)">
        <div slot="toolbar" class="quarter__controls">
            <div>
                <label class="label">Rok</label>
                <input class="input input--primary" type="number" bind:value={year} />
            </div>
            <div>
                <label class="label">Kwartał</label>
                <select class="select select--primary" bind:value={quarter}>
                    <option value="1">Q1</option>
                    <option value="2">Q2</option>
                    <option value="3">Q3</option>
                    <option value="4">Q4</option>
                </select>
            </div>
        </div>

        <div class="quarter__toolbar">
            <Toolbar
                    separated
                    start={[
          { tag:'button', text:(loadingRates?'Ładowanie...':'Pobierz kursy NBP'), props:{ class:'btn btn--ghost', disabled:loadingRates }, on:{ click: loadRates } }
        ]}
                    end={[
          { tag:'button', text:(saving?'Zapisywanie...':'Zapisz'), props:{ class:'btn btn--primary', disabled:saving }, on:{ click: save } }
        ]}
            />
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

                        <!-- kwoty z krokiem 0.01 -->
                        <td><input class="input input--primary input--num" type="number" step="0.01" bind:value={r.professional} /></td>
                        <td><input class="input input--primary input--num" type="number" step="0.01" bind:value={r.pharmacy} /></td>
                        <td><input class="input input--primary input--num" type="number" step="0.01" bind:value={r.b2c} /></td>
                        <td><input class="input input--primary input--num" type="number" step="0.01" bind:value={r.b2b} /></td>
                        <td><input class="input input--primary input--num" type="number" step="0.01" bind:value={r.third} /></td>
                        <td><input class="input input--primary input--num" type="number" step="0.01" bind:value={r.other} /></td>

                        <!-- nowi klienci (int) -->
                        <td><input class="input input--secondary" type="number" step="1" bind:value={r.newClients} /></td>

                        <!-- waluta -->
                        <td>
                            <select class="select select--secondary" bind:value={r.currency}>
                                <option>PLN</option><option>EUR</option><option>USD</option>
                            </select>
                        </td>

                        <td class="cell--num">{fmt(total)} {r.currency}</td>
                        <td class="cell--num">{eur != null ? `${fmt(eur)} EUR` : "–"}</td>
                    </tr>
                {/each}

                <svelte:fragment slot="foot">
                    <tr>
                        <td>RAZEM</td>
                        {#each ['professional','pharmacy','b2c','b2b','third','other'] as k}
                            <td class="cell--num">
                                {fmt(rows.reduce((s,x)=> s + toNum(x[k]), 0))}
                            </td>
                        {/each}
                        <td class="cell--num">{rows.reduce((s,x)=> s + toNum(x.newClients), 0)}</td>
                        <td>—</td>
                        <td class="cell--num">
                            {fmt(rows.reduce((s,x)=> s + sumRow(x), 0))} PLN
                        </td>
                    </tr>
                </svelte:fragment>
            </Table>
        </div>
    </Section>

    <div slot="footer">© BeautyPortal</div>
</Layout>
