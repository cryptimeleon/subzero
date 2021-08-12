<!-- 
    A component to load global CSS stylesheets
 -->
<script>
    import 'carbon-components-svelte/css/all.css';

    import { darkMode } from './stores.js';

    $: document.documentElement.setAttribute("theme", $darkMode ? 'g100' : 'white');
</script>

<style lang='scss' global>
    @import '../node_modules/duckling-markdown-css/github-markdown-dark';
    @import '../node_modules/github-markdown-css/github-markdown';

    :global(body) {
		margin: 0;
		overflow: hidden;
	}
</style>