<!-- 
    A button component to visit the GitHub repo
 -->
<script>
    import { HeaderGlobalAction } from 'carbon-components-svelte';
	import LogoGithub32 from 'carbon-icons-svelte/lib/LogoGithub32';

    function visitGithub() {
        const url = 'https://github.com/cryptimeleon/subzero';
        window.open(url, '_blank').focus();
    }
</script>

<div on:click={visitGithub} on:auxclick={visitGithub}>
    <HeaderGlobalAction aria-label='Source' icon={LogoGithub32}/>
</div>
