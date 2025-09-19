<script>
    import Layout from "../../templates/layout/Layout.svelte";
    import Section from "../../organisms/section/Section.svelte";
    import Table from "../../atoms/table/Table.svelte";
    import Toolbar from "../../molecules/toolbar/Toolbar.svelte";

    // ===== KONFIG API =====
    // 1) Możesz ustawić w .env: VITE_API_BASE=http://localhost:9090/api/v1/sales-channels
    // 2) Albo auto: jeśli front leci z Vite (5173), to wskazujemy backend:9090; w prod zostaje relative.
    const autoBase =
        (typeof window !== "undefined" && window.location.port === "5173")
            ? "http://localhost:9090/api/v1/sales-channels"
            : "/api/v1/sales-channels";

    const API_BASE = (import.meta?.env?.VITE_API_BASE ?? autoBase).replace(/\/$/, "");

    const MONTHS = [
        "Styczeń","Luty","Marzec","Kwiecień","Maj","Czerwiec",
        "Lipiec","Sierpień","Wrzesień","Październik","Listopad","Grudzień"
    ];
    const Q = { 1:[1,2,3], 2:[4,5,6], 3:[7,8,9], 4:[10,11,12] };

    export let distributorId = crypto.randomUUID?.() ?? "";

    let year = new Date().getFullYear();
    let quarter = Math.ceil((new Date().getMonth()+1)/3);

    const newRow = (m) => ({
        month:m, professional:0, pharmacy:0, b2c:0, b2b:0, third:0, other:0,
        newClients:0, currency:"PLN"
    });
    let rows = Q[quarter].map(newRow);
    $: rows = Q[Number(quarter)].map(m => rows.find(r=>r.month===m) || newRow(m));

    let eurRates = {};
    let loadingRates = false;
    let saving = false;

    // ===== UTILS =====
    const toNum = (v) => {
        const n = (v === '' || v == null) ? 0 : +v;
        return Number.isFinite(n) ? n : 0;
    };
    function sumRow(r){
        return toNum(r.professional)+toNum(r.pharmacy)+toNum(r.b2c)+toNum(r.b2b)+toNum(r.third)+toNum(r.other);
    }
    function fmt(n){
        return Number(n||0).toLocaleString(undefined,{ maximumFractionDigits: 2 });
    }

    // ===== KURSY EUR z NBP =====
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
            console.error(e);
            alert("Błąd pobierania kursów NBP.");
        }finally{
            loadingRates = false;
        }
    }

    // ===== ZAPIS RAPORTU do Springa (CORS: 5173 -> 9090) =====
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
                        newClients: Math.max(0, Math.trunc(toNum(r.newClients))),
                        currency: r.currency
                    }
                }))
            };

            const res = await fetch(`${API_BASE}/quarter-reports`, {
                method:"POST",
                headers:{ "Content-Type":"application/json" },
                credentials: "include", // ważne przy cookie/sesji
                body: JSON.stringify(payload)
            });

            if (res.status === 401) {
                // Nie zalogowany → odbij na logowanie (Hosted UI Spring Security)
                const authUrl = `${new URL(API_BASE, window.location.origin).origin}/oauth2/authorization/cognito`;
                window.location.href = authUrl;
                return;
            }

            if (!res.ok) {
                const text = await res.text().catch(()=> "");
                throw new Error(`HTTP ${res.status} ${res.statusText} ${text}`);
            }

            const j = await res.json().catch(()=> ({}));
            alert(j?.id ? ("Raport utworzony: " + j.id) : "Raport utworzony (brak id w odpowiedzi).");
        }catch(e){
            console.error(e);
            alert(`Błąd zapisu raportu. ${e?.message ?? ""}`);
        }finally{
            saving = false;
        }
    }
</script>
