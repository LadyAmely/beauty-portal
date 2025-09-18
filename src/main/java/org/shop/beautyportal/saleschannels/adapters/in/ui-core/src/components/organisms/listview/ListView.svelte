<script>
    export let items = [];
    export let variant = ""; // "" | "cards"
</script>

<style lang="scss">
  @import "listview.scss";
</style>

{#if items.length}
    <ul class={"listview" + (variant ? " listview--" + variant : "")}>
        {#each items as item, i}
            <li class="listview__item">
                <slot {item} {i} />
            </li>
        {/each}
    </ul>
{:else}
    <ul class={"listview" + (variant ? " listview--" + variant : "")}>
        <slot />
    </ul>
{/if}
