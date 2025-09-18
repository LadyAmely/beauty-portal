<script>
    export let value = '';
    export let variant = 'primary';
    export let size = '';
    export let invalid = false;
    export let disabled = false;
    export let name;
    export let id;

    export let items = [];

    $: cls =
        `select select--${variant}` +
        (size ? ` select--${size}` : '') +
        (invalid ? ' is-invalid' : '');
</script>

<style lang="scss">
  @import "select.scss";
</style>

<select
        class={cls}
        bind:value={value}
        {disabled}
        {name}
        {id}
        aria-invalid={invalid}
>
    {#if items.length}
        {#each items as it}
            {#if typeof it === 'object'}
                <option value={it.value}>{it.label ?? it.value}</option>
            {:else}
                <option value={it}>{it}</option>
            {/if}
        {/each}
    {:else}
        <slot />
    {/if}
</select>
