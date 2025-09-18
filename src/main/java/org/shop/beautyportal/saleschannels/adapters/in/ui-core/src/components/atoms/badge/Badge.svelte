
<script>
    export let variant = 'primary';
</script>

<style lang="scss">
  @import "badge.scss";
</style>

<span class={"badge badge--" + variant}>
  <slot />
</span>
