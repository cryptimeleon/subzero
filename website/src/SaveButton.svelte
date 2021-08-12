<!-- 
    A button component to save any text to a file
 -->
<script>
	import { saveAs } from 'file-saver';
	import { HeaderGlobalAction } from 'carbon-components-svelte';
	import Save32 from 'carbon-icons-svelte/lib/Save32';

	import ErrorDialog from './ErrorDialog.svelte';
	import FileInputDialog from './FileInputDialog.svelte';
	
	export let data;
	export let dataName;
	export let extension;
	export let ref = undefined;

	let openErrorDialog = false;
	let openDownloadDialog = false;
	let filename = '';

	function downloadData() {
		if (data.length === 0) {
			openErrorDialog = true;
		} else {
			openDownloadDialog = true;
		}
	}

	function saveFile() {
		const blob = new Blob([data], {type: 'text/plain;charset=utf-8'});
		saveAs(blob, filename + '.' + extension, data);
	}
</script>

<HeaderGlobalAction
	bind:ref={ref}
    aria-label='Save Subzero code'
    icon={Save32}
    on:click={downloadData}
/>

<ErrorDialog 
	bind:open={openErrorDialog}
	message='There is no {dataName} to download'
	on:close={() => {openErrorDialog = false}}
/>

<FileInputDialog 
    bind:open={openDownloadDialog}
    bind:value={filename}
    heading='Save {dataName}'
    on:submit={saveFile}
/>