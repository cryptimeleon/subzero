export function conditionalEventListener(node, {condition, type, listener}) {
    if (!condition) return;
    
    node.addEventListener(type, listener);

    return {
        destroy() {
            node.removeEventListener(type, listener);
        }
    }
}