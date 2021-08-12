<script>
    import { Accordion, AccordionItem, DataTable } from 'carbon-components-svelte';

    import { conditionalEventListener } from './actions';
    import { environment } from './stores';

    const tableColors = {
        'witness': '#94def6', // Blue
        'public parameter': '#f694ac', // Red
        'common input': '#94f6ac', // Green
        'boolean': '#f6de94', // Yellow
        'exponent': '#52edc9', // Turquoise
        'group element': '#a394f6', // Purple
        'group element (G1)': '#a394f6', // Purple
        'group element (G2)': '#d6aaf6', // Periwinkle
        'group element (GT)': '#f6aaef', // Pink
    };

    let tableFunctions = [];
    let tableVariables = [];
    let openFunctions = true;
    let openVariables = true;

    // Runs when $environment is changed
    $: {
        let variables = $environment.filter((element) => element.construct === 'variable');
        let functions = $environment.filter((element => element.construct === 'function'));

        createTableVariables(variables);
        createTableFunctions(functions);
    }

    function createTableVariables(variables) {
        tableVariables = [];
        
        variables.forEach((variable, index) => {
            const variableType = variable.type + 
                ('group' in variable ? ' (' + variable.group + ')' : '');

            const tableVariable = {
                id: index+1,
                name: variable.name,
                type: variableType,
                role: variable.role,
            }

            tableVariables.push(tableVariable);
        });
    }

    function createTableFunctions(functions) {
        tableFunctions = [];

        functions.forEach((func, index) => {
            const tableFunction = {
                id: index+1,
                name: func.name,
                parameterNames: func.parameterNames.join('|'),
                parameterTypes: func.parameterTypes.join('|'),
                returnType: func.returnType,
                origin: func.origin
            }

            tableFunctions.push(tableFunction);
        });
    }

    function setCellTitle() {
        if (this.title) {
            if (this.offsetWidth >= this.scrollWidth) {
                this.title = '';
            }
        } else {
            if (this.offsetWidth < this.scrollWidth) {
                this.title = this.innerHTML;
            }
        }
    }
</script>

<div class='environment'>
    <Accordion>
        <AccordionItem bind:open={openFunctions} title='Functions'>
            <div class='functions-container'>
                {#if tableFunctions.length === 0}
                    <p class='empty-table-message'>
                        Any protocol functions will be displayed here
                    </p>
                {:else}
                    <div class={'functions-table-container table-container ' + (openVariables ? 'small-container' : 'full-container')}>
                        <DataTable
                            useStaticWidth
                            sortable
                            headers={[
                                {key: 'name', value: 'Function name'},
                                {key: 'parameterNames', value: 'Parameter names', sort: false},
                                {key: 'parameterTypes', value: 'Parameter types', sort: false},
                                {key: 'returnType', value: 'Return type'},
                                {key: 'origin', value: 'Origin'}
                            ]}
                            rows={tableFunctions}
                        >
                            <div
                                let:cell
                                use:conditionalEventListener={{
                                    condition: cell.key === 'name',
                                    type: 'mouseenter',
                                    listener: setCellTitle
                                }}
                                class='table-cell'
                                slot='cell'
                            >
                                {#if cell.key === 'returnType'}
                                    <p style='color: {tableColors[cell.value]}'>
                                        {cell.value}
                                    </p>
                                {:else if cell.key === 'parameterNames'}
                                    {#each cell.value.split('|') as parameterName}
                                        <p class='table-cell' on:mouseenter={setCellTitle}>
                                            {parameterName}
                                        </p>
                                    {/each}
                                {:else if cell.key === 'parameterTypes'}
                                    {#each cell.value.split('|') as parameterType}
                                        <p style='color: {tableColors[parameterType]}'>
                                            {parameterType}
                                        </p>
                                    {/each}
                                {:else}
                                    {cell.value}
                                {/if}
                            </div>
                        </DataTable>
                    </div>
                {/if}
            </div>
        </AccordionItem>
        <AccordionItem bind:open={openVariables} title='Variables'>
            <div class='variables-container'>
                {#if tableVariables.length === 0}
                    <p class='empty-table-message'>
                        Any protocol variables will be displayed here
                    </p>
                {:else}
                    <div class={'variables-table-container table-container ' + (openFunctions ? 'medium-container' : 'full-container')}>
                        <DataTable
                            sortable
                            headers={[
                                {key: 'name', value: 'Variable name'},
                                {key: 'type', value: 'Type'},
                                {key: 'role', value: 'Role'},
                            ]}
                            rows={tableVariables}
                        >
                            <div
                                let:cell
                                use:conditionalEventListener={{
                                    condition: cell.key === 'name',
                                    type: 'mouseenter',
                                    listener: setCellTitle
                                }}
                                class='table-cell'
                                slot='cell'
                            >
                                {#if cell.key === 'role' || cell.key === 'type'}
                                    <span style='color: {tableColors[cell.value]}'>
                                        {cell.value}
                                    </span>
                                {:else}
                                    {cell.value}
                                {/if}
                            </div>
                        </DataTable>
                    </div>
                {/if}
            </div>
        </AccordionItem>
    </Accordion>
</div>

<style>
    .environment {
        display: flex;
        flex-flow: column;
        height: 100%;
    }

    .environment :global(.bx--accordion__title) {
        font-size: 1.25rem;
    }

    .environment :global(.bx--accordion__content) {
        padding: 0;
    }

    .functions-container {
        height: 100%;
        width: 100%;
    }

    .variables-container {
        height: 100%;
        width: 100%;
    }

    p.empty-table-message {
        padding: 1rem;
        font-size: 1rem;
    }

    .functions-table-container {
        height: 100%;
    }

    .variables-table-container {
        height: 100%;
    }

    .functions-table-container :global(td), .functions-table-container :global(th) {
        width: 20%;
    }

    .variables-table-container :global(td), .variables-table-container :global(th) {
        width: 33.3%;
    }

    .table-container :global(.bx--table-header-label) {
        text-align: left;
    }

    .small-container :global(tbody) {
        min-height: 10.5rem;
        max-height: 10.5rem;
    }

    .medium-container :global(tbody) {
        min-height: 23rem;
        max-height: 23rem;
    }

    .full-container :global(tbody) {
        min-height: 36.5rem;
        max-height: 36.5rem;
    }

    .table-cell {
        font-size: 1rem;
        text-overflow: ellipsis;
        overflow: hidden;
    }
    
    /* Makes the tables scrollable */
    .table-container :global(tbody) {
        display: block;
        overflow-y: scroll;
    }

    .table-container :global(thead), .table-container :global(tbody tr) {
        display: table;
        width: 100%;
        table-layout: fixed;
    }
    /**********/

    /* Styles the table scrollbar */
    .table-container :global(tbody::-webkit-scrollbar) {
        width: 0.5rem;
    }
    
    .table-container :global(tbody::-webkit-scrollbar-track) {
    box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
    }
    
    .table-container :global(tbody::-webkit-scrollbar-thumb) {
    background-color: darkgrey;
    outline: 1px solid slategrey;
    }
    /**********/
</style>