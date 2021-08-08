<script>
    import { DataTable } from 'carbon-components-svelte';

    import { environment } from './stores';

    const tableColors = {
        'witness': '#94def6', // Blue
        'public parameter': '#f694ac', // Red
        'common input': '#94f6ac', // Green
        'exponent': '#52edc9', // Turquoise
        'group element': '#a394f6', // Purple
        'group element (G1)': '#a394f6', // Purple
        'group element (G2)': '#d6aaf6', // Periwinkle
        'group element (GT)': '#f6aaef', // Pink
    };

    let tableVariables = [];
    let tableFunctions = [];

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
                parameterTypes: func.parameterTypes.join('|'),
                returnType: func.returnType,
                origin: func.origin
            }

            tableFunctions.push(tableFunction);
        });
    }
    
    function formatParameterTypes(parameterTypesString) {
        const parameterTypes = parameterTypesString.split('|');
        const length = parameterTypes.length;
        const formattedParameterTypes = [];

        parameterTypes.forEach((parameterType, index) => {
            formattedParameterTypes.push([parameterType, index < length-1]);
        });

        return formattedParameterTypes;
    }
</script>

<div class='environment'>
    <div class='functions-container'>
        <p class='table-title'>
            Functions
        </p>
        {#if tableFunctions.length === 0}
            <p class='empty-table-message'>
                Any protocol functions will be displayed here
            </p>
        {:else}
            <div class='functions-table-container table-container'>
                <DataTable
                    useStaticWidth
                    sortable
                    headers={[
                        {key: 'name', value: 'Function name'},
                        {key: 'parameterTypes', value: 'Parameter types', sort: false},
                        {key: 'returnType', value: 'Return type'},
                        {key: 'origin', value: 'Origin'}
                    ]}
                    rows={tableFunctions}
                >
                    <p slot='cell' let:cell>
                        {#if cell.key === 'returnType'}
                            <span style='color: {tableColors[cell.value]}'>
                                {cell.value}
                            </span>
                        {:else if cell.key === 'parameterTypes'}
                            {#each formatParameterTypes(cell.value) as [parameterType, addComma]}
                                <!-- This must be all on one line, otherwise an extra space char will be inserted between the type and the comma after it -->
                                <span style='color: {tableColors[parameterType]}'>{parameterType}</span>{#if addComma}<span>, </span>{/if}
                            {/each}
                        {:else}
                            {cell.value}
                        {/if}
                    </p>
                </DataTable>
            </div>
        {/if}
    </div>

    <div class='variables-container'>
        <p class='table-title'>
            Variables
        </p>
        {#if tableVariables.length === 0}
            <p class='empty-table-message'>
                Any protocol variables will be displayed here
            </p>
        {:else}
            <div class='variables-table-container table-container'>
                <DataTable
                    sortable
                    headers={[
                        {key: 'name', value: 'Variable name'},
                        {key: 'type', value: 'Type'},
                        {key: 'role', value: 'Role'},
                    ]}
                    rows={tableVariables}
                >
                    <p slot='cell' let:cell>
                        {#if cell.key === 'role' || cell.key === 'type'}
                            <span style='color: {tableColors[cell.value]}'>
                                {cell.value}
                            </span>
                        {:else}
                            {cell.value}
                        {/if}
                    </p>
                </DataTable>
            </div>
        {/if}
    </div>
</div>

<style>
    .environment {
        display: flex;
        flex-flow: column;
        height: 100%;
    }

    .functions-container {
        height: 100%;
        flex-grow: 1;
    }

    .variables-container {
        height: 100%;
        flex-grow: 2;
    }

    p.table-title {
        padding: 0.25rem;
        padding-left: 1rem;
        font-size: 1.5rem;
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

    /* Set the column widths for the functions table */
    .functions-table-container :global(td:nth-child(1)), .functions-table-container  :global(th:nth-child(1)) {
        width: 20%;
    }

    .functions-table-container :global(td:nth-child(2)), .functions-table-container  :global(th:nth-child(2)) {
        width: 40%;
    }

    .functions-table-container :global(td:nth-child(3)), .functions-table-container  :global(th:nth-child(3)) {
        width: 15%;
    }

    .functions-table-container :global(td:nth-child(4)), .functions-table-container  :global(th:nth-child(4)) {
        width: 15%;
    }
    /**********/

    /* Set the column widths for the variables table */
    .variables-table-container :global(td:nth-child(1)), .variables-table-container :global(th:nth-child(1)) {
        width: 33.3%;
    }

    .variables-table-container :global(td:nth-child(2)), .variables-table-container  :global(th:nth-child(2)) {
        width: 33.4%;
    }

    .variables-table-container :global(td:nth-child(3)), .variables-table-container  :global(th:nth-child(3)) {
        width: 33.3%;
    }
    /**********/
    
    /* Makes the tables scrollable */
    .table-container :global(tbody) {
        display: block;
        overflow-y: scroll;
    }

    .functions-container .table-container :global(tbody) {
        max-height: 11rem;
    }

    .variables-container .table-container :global(tbody) {
        max-height: 23rem;
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