<script>
    import { onDestroy } from 'svelte';
    export let api;
    export let duration = 3000;
    let items = [];
    let id = 0;

    function push(text, variant = 'primary', ms = duration) {
        const item = { id: ++id, text, variant, t: null };
        items = [...items, item];
        item.t = setTimeout(() => remove(item.id), ms);
    }
    function remove(i) {
        const it = items.find(x => x.id === i);
        if (it?.t) clearTimeout(it.t);
        items = items.filter(x => x.id !== i);
    }
    function clear() {
        items.forEach(x => x.t && clearTimeout(x.t));
        items = [];
    }
    $: api = { push, clear };
    onDestroy(clear);
</script>

<style lang="scss">
  @import "toast.scss";
</style>

<div class="toast">
    {#each items as it (it.id)}
        <div class={"toast__item toast__item--" + it.variant} on:click={() => remove(it.id)}>
            {it.text}
        </div>
    {/each}
</div>
