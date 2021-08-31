export function setTitleOnOverflow() {
    this.title = (this.offsetWidth >= this.scrollWidth) ? '' : this.innerHTML;
}