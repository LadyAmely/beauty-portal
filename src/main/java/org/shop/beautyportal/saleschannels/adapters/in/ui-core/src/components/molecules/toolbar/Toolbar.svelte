
<script>
    export let separated = false;
</script>

<style lang="scss">
  @import "toolbar.scss";
</style>

<div class={"toolbar" + (separated ? " toolbar--separated" : "")}>
    {#if $$slots.start || $$slots.end}
        <div class="toolbar__start"><slot name="start" /></div>
        <span class="toolbar__spacer"></span>
        <div class="toolbar__end"><slot name="end" /></div>
    {:else}
        <slot />
    {/if}
</div>
