import { writable, readable } from 'svelte/store';
import { loadFromStorage } from './storage.js';

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

const projectOptions = {
    full: 'Full project',
    protocol: 'Protocol class only',
    publicParameters: 'Public parameters class only',
    test: 'Test class only',
};
export const generatedProjectOptions = readable(projectOptions);

export const includeCode = writable(loadFromStorage('includeCode', true));
export const includeLatex = writable(loadFromStorage('includeLatex', true));
export const openClassFilesInNewTabs = writable(loadFromStorage('openClassFilesInNewTabs', false));
export const generatedProject = writable(loadFromStorage('generatedProject', projectOptions.full));
export const darkMode = writable(loadFromStorage('darkMode', true));
export const darkTheme = writable(loadFromStorage('darkTheme', ''));
export const lightTheme = writable(loadFromStorage('lightTheme', ''));