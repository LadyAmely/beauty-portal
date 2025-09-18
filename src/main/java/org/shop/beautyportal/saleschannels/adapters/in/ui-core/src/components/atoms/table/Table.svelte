
<script>
    export let variant = '';
    export let caption = '';
</script>

<style lang="scss">
  @import "table.scss";
</style>

<div class="table__wrap">
    <table class={"table" + (variant ? " table--" + variant : "")}>
        {#if caption}<caption>{caption}</caption>{/if}
        {#if $$slots.head}<thead><slot name="head" /></thead>{/if}
        <tbody><slot /></tbody>
        {#if $$slots.foot}<tfoot><slot name="foot" /></tfoot>{/if}
    </table>
</div>
