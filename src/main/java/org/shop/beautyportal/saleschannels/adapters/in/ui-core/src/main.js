
import { mount } from 'svelte';
import QuarterReport from './components/pages/quarterreport/QuarterReport.svelte';

console.log('[Svelte] boot…')

mount(QuarterReport, {
    target: document.getElementById('app'),
    props: {}
});
