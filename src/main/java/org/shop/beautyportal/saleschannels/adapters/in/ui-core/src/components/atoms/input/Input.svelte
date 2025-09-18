
<script>
    export let value = '';
    export let variant = 'primary';
    export let size = '';
    export let num = false;
    export let invalid = false;
    export let type = 'text';
    export let placeholder = '';
    export let name;
    export let id;
    export let disabled = false;
    export let readonly = false;

    $: cls =
        `input input--${variant}` +
        (size ? ` input--${size}` : '') +
        (num ? ' input--num' : '') +
        (invalid ? ' is-invalid' : '');

    function onInput(e) {
        value = e.currentTarget.value;
    }
</script>

<style lang="scss">
    @import "input.scss";
</style>

<input
        class={cls}
        value={value}
        {type}
        {placeholder}
        {name}
        {id}
        {disabled}
        {readonly}
        aria-invalid={invalid ? 'true' : undefined}
        on:input={(e) => value = e.currentTarget.value}
/>
