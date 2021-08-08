<script>
    export let position = 'bottom';
    export let size = 'small';
    export let text;

    const className = `tooltip tooltip-${size} tooltip-${position}`;
</script>

<div class='tooltip-container'>
    <div class={className} data-tooltip={text}>
        <slot/>
    </div>
</div>

<style>
    .tooltip {
		margin: 0;
		z-index: 10;
		font-size: 0.875rem;
		font-weight: 400;
	}

	.tooltip:after {
		opacity: 0;
		content: '';
		z-index: 10;
		text-align: center;
	}

	.tooltip:hover:after { 
		opacity: 1;
		transition: opacity  0s linear 0.5s;
		content: attr(data-tooltip);
		position: absolute;
		padding: 5px;
		border-radius: 5px;
		background-color: #393939;
		color: white;
		z-index: 10;
	}

    .tooltip-container :global(.tooltip-small:hover:after) {
		width: 5rem;
    }

    .tooltip-container :global(.tooltip-medium:hover:after) {
        width: 10rem;
    }

    .tooltip-container :global(.tooltip-large:hover:after) {
        width: 15rem;
    }

    .tooltip-container :global(.tooltip-bottom:hover:after) {
		top: 3.5rem;
        left: 50%;
        transform:translateX(-50%);
    }

    .tooltip-container :global(.tooltip-top:hover:after) {
        top: -3rem;
        left: 50%;
        transform:translateX(-50%);
    }

    .tooltip-container :global(.tooltip-left:hover:after) {
        top: 3.5rem;
        left: 50%;
        transform:translateX(-75%);
    }

    .tooltip-container :global(.tooltip-right:hover:after) {
        top: 3.5rem;
        left: 50%;
        transform:translateX(25%);
    }

    .tooltip-container {
		position: relative;
	}
</style>