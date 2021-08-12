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

    // Fixes issue where syntax/semantic errors are not underlined in red
    // CSS is from xtext-web source code
    :global(.xtext-marker_error) {
        position: absolute;
        z-index: 30;
        background-image: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAQAAAADCAYAAAC09K7GAAAABmJLR0QA/wD/AP+gvaeTAAAAHElEQVQI12NggIL/DAz/GdA5/xkY/qPKMDAwAADLZwf5rvm+LQAAAABJRU5ErkJggg==");
        background-repeat: repeat-x;
        background-position: left bottom;
    }

    // Fixes issue where syntax/semantic warnings are not underlined in yellow
    // CSS is from xtext-web source code
    :global(.xtext-marker_warning) {
        position: absolute;
        z-index: 20;
        background-image: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAQAAAADCAYAAAC09K7GAAAABmJLR0QA/wD/AP+gvaeTAAAAMklEQVQI12NkgIIvJ3QXMjAwdDN+OaEbysDA4MPAwNDNwMCwiOHLCd1zX07o6kBVGQEAKBANtobskNMAAAAASUVORK5CYII=");
        background-repeat: repeat-x;
        background-position: left bottom;
    }
</style>