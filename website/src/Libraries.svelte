<!-- 
    A component to load all libraries that cannot be imported through NPM
 -->
<svelte:head>
    <script 
        bind:this={script}
        src='https://cdnjs.cloudflare.com/ajax/libs/require.js/2.3.6/require.min.js'>
    </script>
</svelte:head>

<script>
    import { onMount } from 'svelte';

    import { BASE_URL, xtext, ace } from './stores.js';

    let script;

    onMount(async () => {
        script.addEventListener('load', () => {
            loadLibraries();
        });
    });

    function loadLibraries() {
        require.config({
            baseUrl: $BASE_URL,
            paths: {
                'jquery': 'webjars/jquery/3.5.1/jquery.min',
                'ace/ext/language_tools': 'webjars/ace/1.3.3/src/ext-language_tools',
                'xtext/xtext-ace': 'xtext/2.24.0/xtext-ace',
            }
        });

        require(['webjars/ace/1.3.3/src/ace'], function() {
            require(['xtext/xtext-ace'], function(xtextLibrary) {
                $xtext = xtextLibrary;
                $ace = window.ace;
            });
        });
    }
</script>