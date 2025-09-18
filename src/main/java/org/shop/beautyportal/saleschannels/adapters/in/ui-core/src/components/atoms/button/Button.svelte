<script>
  export let variant = "primary";
  export let disabled = false;
</script>

<style lang="scss">
  @import "../../../styles/_variables.scss";
  @import "button.scss";
</style>

<button
  class="btn btn--{variant}"
  {disabled}
>
  <slot />
</button>
