
<script>
    export let compact = false;
    export let stats = [];
</script>

<style lang="scss">
  @import "report.scss";
</style>

<div class={"report" + (compact ? " report--compact" : "")}>
    {#if stats.length}
        <div class="report__grid">
            {#each stats as s}
                <div class="report__stat">
                    <p class="report__stat-title">{s.title}</p>
                    <div class="report__stat-value">{s.value}</div>
                </div>
            {/each}
        </div>
    {/if}

    {#if $$slots.grid}
        <div class="report__grid"><slot name="grid" /></div>
    {/if}

    <section class="report__section">
        <slot />
    </section>

    {#if $$slots.totals}
        <div class="report__totals"><slot name="totals" /></div>
    {/if}

    {#if $$slots.note}
        <p class="report__note"><slot name="note" /></p>
    {/if}
</div>
