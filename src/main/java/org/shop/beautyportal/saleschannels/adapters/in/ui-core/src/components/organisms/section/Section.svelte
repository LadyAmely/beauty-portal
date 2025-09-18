
<script>
    export let title = "";
    export let description = "";
    export let compact = false;
    export let flush = false;

    $: cls =
        "section" +
        (compact ? " section--compact" : "") +
        (flush ? " section--flush" : "");
</script>

<style lang="scss">
  @import "section.scss";
</style>

<section class={cls}>
    <div class="section__header">
        {#if title}<h3 class="section__title">{title}</h3>{/if}
        {#if description}<p class="section__desc">{description}</p>{/if}
        {#if $$slots.toolbar}<div class="section__toolbar"><slot name="toolbar" /></div>{/if}
    </div>
    <div class="section__body">
        <slot />
    </div>
</section>
