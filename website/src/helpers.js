export function setTitleOnOverflow() {
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