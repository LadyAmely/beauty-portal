<script>
    export let sidebar = true;
</script>

<style lang="scss">
  @import "layout.scss";
</style>

<div class={"layout" + (sidebar ? "" : " layout--no-sidebar")}>
    <header class="layout__header">
        <div class="layout__container">
            <slot name="header" />
        </div>
    </header>

    <div class="layout__container">
        <div class="layout__body">
            {#if sidebar}
                    <aside class="layout__sidebar">
                        <slot name="sidebar" />
                    </aside>
                {/if}
            <main class="layout__main">
                <slot />
            </main>
        </div>
    </div>

    <footer class="layout__footer">
        <div class="layout__container">
            <slot name="footer" />
        </div>
    </footer>
</div>
