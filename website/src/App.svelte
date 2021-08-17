<script>
	import Theme from './Theme.svelte';
	import Libraries from './Libraries.svelte';	
	import Header from './Header.svelte';
	import Content from './Content.svelte';
</script>

<Theme/>
<Libraries/>

<div class='page'>
	<Header/>
	<Content/>
</div>

<style>
	:global(html), :global(body) {
		position: relative;
		width: 100%;
		height: 100%;
	}

	.page {
		width: 100%;
		height: 100%;
		display: flex;
		flex-flow: column;
	}
</style>