<script>
    import { HeaderGlobalAction }  from 'carbon-components-svelte';
    import Awake32 from 'carbon-icons-svelte/lib/Awake32';
    import Asleep32 from 'carbon-icons-svelte/lib/Asleep32';

    import { darkMode } from './stores.js';

    let icon = Awake32;

    function toggleDarkMode() {
        $darkMode = !$darkMode;
        icon = $darkMode ? Awake32: Asleep32;
    }
</script>

<HeaderGlobalAction
    aria-label='Toggle dark mode'
    {icon}
    on:click={toggleDarkMode}
/>