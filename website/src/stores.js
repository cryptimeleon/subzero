import { writable, readable } from 'svelte/store';

const baseUrl = window.location.pathname;
const fileIndex = baseUrl.indexOf('index.html');
if (fileIndex > 0) {
    baseUrl = baseUrl.slice(0, fileIndex);
}

export const BASE_URL = readable(baseUrl);

const extension = 'sub0';
const uniqueId = Math.random().toString(36).substring(2, 12);
export const DSL_EXTENSION = readable(extension);

export const CODE_RESOURCE_ID = writable(`${uniqueId}.code.${extension}`);
export const LATEX_RESOURCE_ID = writable(`${uniqueId}.latex.${extension}`);

export const latexText = writable('');
export const xtextEditor = writable(undefined);
export const editorCode = writable('');
export const editorCodeHasChanged = writable(false);
export const xtext = writable(undefined);
export const ace = writable(undefined);
export const environment = writable([]);
export const javaClasses = writable({});

export const includeCode = writable(true);
export const includeLatex = writable(true);
export const openClassFilesInNewTabs = writable(false);
export const generatedProject = writable('Full project');
export const darkMode = writable(true);