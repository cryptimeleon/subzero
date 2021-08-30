<!-- 
    A component for compiling the Subzero code and generating the Java project
 -->
<script>
    import JSZip from 'jszip';
    import { saveAs } from 'file-saver';
    
    import { Button, CodeSnippet, Link, Loading, Modal } from 'carbon-components-svelte';
    
    import {
        CODE_RESOURCE_ID,
        DSL_EXTENSION,
        editorCode,
        generatedProject,
        generatedProjectOptions,
        includeCode,
        includeLatex,
        javaClasses,
        openClassFilesInNewTabs,
        xtextEditor,
    } from './stores.js';
    import { validateCode } from './validation.js';

    import ErrorDialog from './ErrorDialog.svelte';
    import FileInputDialog from './FileInputDialog.svelte';
    import Tooltip from './Tooltip.svelte';

    $: editor = $xtextEditor;

    let isCompiling = false;
    let filename;
    let openDownloadDialog = false;
    let openErrorDialog = false;
    let openTraceDialog = false;
    let errorMessage = '';
    let stackTrace = '';
    let saveProject;

    // Compile the contents of the code editor to Java code and download the generated Java project
    function compileCode() {
        if ($editorCode.length === 0) {
            openErrorDialog = true;
            errorMessage = 'There is no Subzero code to compile.';
            return;
        }

        isCompiling = true;

        validateCode($CODE_RESOURCE_ID, (isValid, issues) => {
            if (isValid) {
                editor.xtextServices.generatorService._encodedResourceId = $CODE_RESOURCE_ID;
                editor.xtextServices.generate().then((projectJSON) => {
                    isCompiling = false;

                    if (projectJSON.length === 0) {
                        openErrorDialog = true;
                        errorMessage = 'There is no generated code to download.';
                        return;
                    } else {
                        let project = jQuery.parseJSON(projectJSON);
                        if ('error' in project) {
                            handleCompileError(project);
                        } else {
                            createProject(project);
                        }
                    }
                });
            } else {
                isCompiling = false;
                openErrorDialog = true;
                errorMessage = 'Code cannot be compiled until there are no syntax or semantic errors.';
            }
        });
    }

    function handleCompileError(project) {
        stackTrace = project.error;
        openTraceDialog = true;
    }

    function createProject(project) {
        let protocolName = '';
        for (const key in project.prototype) {
            if (key.endsWith($DSL_EXTENSION)) {
                protocolName = key.substring(0, key.indexOf('.'));
            }
        }

        filename = '';
        let code;
        let protocolFilename = protocolName + '.java';
        let testFilename = 'LibraryTest.java';
        let ppFilename = protocolName + 'PublicParameters.java';
        switch ($generatedProject) {
            case $generatedProjectOptions.protocol:
                filename = protocolFilename;
                code = project.prototype.src.main.java.prototype[filename];
                if ($openClassFilesInNewTabs) $javaClasses = {[filename]: code};
                saveCode(code, filename);
                break;
            case $generatedProjectOptions.test:
                filename = testFilename;
                code = project.prototype.src.test.java.prototype[filename];
                if ($openClassFilesInNewTabs) $javaClasses = {[filename]: code};
                saveCode(code, filename);
                break;
            case $generatedProjectOptions.publicParameters:
                filename = ppFilename;
                code = project.prototype.src.main.java.prototype[filename];

                if (!code) {
                    openErrorDialog = true;
                    errorMessage = 'Cannot download only the public parameters class when the protocol contains no public parameters.';
                    return;
                }

                if ($openClassFilesInNewTabs) $javaClasses = {[filename]: code};
                saveCode(code, filename);
                break;
            case $generatedProjectOptions.full:
                let projectZip = new JSZip();
                createProjectZip(projectZip, project);

                saveProject = () => {
                    openDownloadDialog = false;

                    if (filename.length > 0) {
                        if ($openClassFilesInNewTabs) {
                            let protocolCode = project.prototype.src.main.java.prototype[protocolFilename];
                            let testCode = project.prototype.src.test.java.prototype[testFilename];
                            let ppCode = project.prototype.src.main.java.prototype[ppFilename];
                            $javaClasses = {
                                [protocolFilename]: protocolCode,
                                [testFilename]: testCode,
                                ...(ppCode.length > 0 && {[ppFilename]: ppCode})
                            };
                        }

                        projectZip.generateAsync({type:'blob', platform:'UNIX'})
                            .then(function(blob) {
                                saveAs(blob, filename);
                            });
                    }
                }
            
                openDownloadDialog = true;
                break;
        }
    }

    function createProjectZip(zip, currentFolder) {
        for (const [name, contents] of Object.entries(currentFolder)) {
            if (jQuery.type(contents) === 'string') {
                if (name.endsWith('.jar')) {
                    zip.file(name, contents, {base64: true});
                } else if (!name.includes('.')) {
                    zip.file(name, contents, {unixPermissions: '775'});
                } else if (
                    ($includeCode || !name.endsWith($DSL_EXTENSION)) &&
                    ($includeLatex || !name.endsWith('tex'))
                ) {
                    zip.file(name, contents);
                }
            } else {
                createProjectZip(zip.folder(name), contents);
            }
        }
    }

    function saveCode(code, filename) {
        let blob = new Blob([code], {type: 'text/plain;charset=utf-8'});
        saveAs(blob, filename);
    }
</script>

<Tooltip size='large' position='top' text='Compiles and downloads a .zip containing the Java project generated by the Subzero code'>
    <div class='compile-button'>
        <Button on:click={compileCode}>
            Compile
        </Button>
    </div>
</Tooltip>

<ErrorDialog
    bind:open={openErrorDialog}
    bind:message={errorMessage}
    on:close={() => openErrorDialog = false}
/>

<FileInputDialog 
    bind:open={openDownloadDialog}
    bind:value={filename}
    heading='Save generated Java project'
    description='The project will be saved with .zip extension'
    on:submit={saveProject}
/>

<Modal
	bind:open={openTraceDialog}
    passiveModal
	modalHeading='Uh-oh'
	on:close={() => {openTraceDialog = false}}
>
    <p>An error occurred during compilation. The stack trace is included below. Please consider opening a <Link href='https://github.com/cryptimeleon/subzero/issues/new'>GitHub issue</Link>.</p>
    <br/>
    {#key stackTrace}
        <div class='code-snippet'>
                <CodeSnippet showMoreLess={false} type='multi' code={stackTrace} />
        </div>
    {/key}
</Modal>

{#if isCompiling}
    <div class='loading'>
        <Loading withOverlay={false} description='Compiling'/>
    </div>
{/if}

<style>
    .compile-button {
        width: 25rem;
        display: flex;
        justify-content: center;
        padding: 1rem;
    }

    .compile-button :global(.tooltip-container) {
        width: 100%;
        display: flex;
        justify-content: center;
        padding: 1rem;
    }

    .compile-button :global(.bx--btn--primary) {
        width: 100%;
        padding: 0;
        font-size: 1rem;
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .loading {
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        z-index: 1000;
    }
</style>