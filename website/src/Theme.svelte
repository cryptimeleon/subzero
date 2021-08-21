<!-- 
	A component to load global CSS stylesheets
 -->
<script>
	import 'carbon-components-svelte/css/all.css';

	import { darkMode } from './stores.js';

	$: document.documentElement.setAttribute('theme', $darkMode ? 'g100' : 'white');
</script>

<style lang='scss' global>
	@import '../node_modules/duckling-markdown-css/github-markdown-dark';
	@import '../node_modules/github-markdown-css/github-markdown';

	:global(body) {
		margin: 0;
		overflow-x: hidden;
	}

	:global(::-webkit-scrollbar) {
		width: 0.75rem;
		height: 0.75rem;
	}
	
	:global(::-webkit-scrollbar-track) {
		box-shadow: inset 0 0 6px rgba(240, 202, 202, 0.3);
	}
	
	:global(::-webkit-scrollbar-thumb) {
		background-color: darkgrey;
		outline: 1px solid slategrey;
	}

	:global(::-webkit-scrollbar-corner) {
		box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
	}
</style>