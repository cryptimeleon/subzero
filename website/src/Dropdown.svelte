<!-- 
    A wrapper for the carbon-svelte Dropdown component to fix a bug
    where the dropdown arrow appears too low
 -->
<script>
    import { Dropdown } from 'carbon-components-svelte';
    import { onMount } from 'svelte';

    import { setTitleOnOverflow } from './helpers.js';

    export let selectedIndex;
    export let items;
    export let light = false;
    export let titleText = '';
    export let hideLabel = false;
    export let label = '';

    let dropdownContainer;

    onMount(() => {
        const parentNode = dropdownContainer.lastElementChild.lastElementChild.lastElementChild;
        const labelNode = parentNode.firstElementChild;
        const iconNode = parentNode.lastElementChild;

        // Add overflow title event listener to each list item and selected item
        dropdownContainer.addEventListener('click', addTitleListeners);
        labelNode.addEventListener('mouseenter', setTitleOnOverflow);

        // Switching the order of the dropdown box element and the arrow icon 
        // element fixes the dropdown arrow issue
        parentNode.insertBefore(iconNode, labelNode);
    });

    function addTitleListeners() {
        const listBoxNode = dropdownContainer.lastElementChild.lastElementChild;

        if (listBoxNode.firstElementChild !== listBoxNode.lastElementChild) {
            for (const element of listBoxNode.lastElementChild.children) {
                const example = element.lastElementChild;
                example.addEventListener('mouseenter', setTitleOnOverflow);
            }
        }
    }
</script>

<div class='dropdown-container' bind:this={dropdownContainer} >
    <Dropdown
        bind:selectedIndex={selectedIndex}
        titleText={titleText}
        light={light}
        hideLabel={hideLabel}
        label={label}
        items={items}
    />
</div>

<style>
    .dropdown-container {
        width: 100%;
    }
</style>