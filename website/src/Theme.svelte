<!-- 
    A component to load global CSS stylesheets
 -->
<script>
    import 'carbon-components-svelte/css/g100.css';
</script>

<style lang='scss' global>
    @import '../node_modules/duckling-markdown-css/github-markdown-dark';

    :global(body) {
		margin: 0;
		overflow: hidden;
	}
</style>