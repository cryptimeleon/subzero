import svelte from 'rollup-plugin-svelte';
import commonjs from '@rollup/plugin-commonjs';
import resolve from '@rollup/plugin-node-resolve';
import livereload from 'rollup-plugin-livereload';
import { terser } from 'rollup-plugin-terser';
import css from 'rollup-plugin-css-only';
import autoPreprocess from 'svelte-preprocess';
import copy from 'rollup-plugin-copy';
import { optimizeImports } from "carbon-preprocess-svelte";

const production = !process.env.ROLLUP_WATCH;
const outputFolder = (production ? '' : '../org.cryptimeleon.subzero.parent/')
	+ 'org.cryptimeleon.subzero.web/src/main/webapp';

function serve() {
	let server;

	function toExit() {
		if (server) server.kill(0);
	}

	return {
		writeBundle() {
			if (server) return;
			server = require('child_process').spawn('npm', ['run', 'start', '--', '--dev'], {
				stdio: ['ignore', 'inherit', 'inherit'],
				shell: true
			});

			process.on('SIGTERM', toExit);
			process.on('exit', toExit);
		}
	};
}

export default {
	input: 'src/main.js',
	output: {
		sourcemap: true,
		format: 'iife',
		name: 'app',
		file: `${outputFolder}/build/bundle.js`
	},
	plugins: [
		svelte({
			compilerOptions: {
				// Enable run-time checks when not in production
				dev: !production
			},
			preprocess: [
				// Preprocesses scss
				autoPreprocess({
					postcss: true,
					scss: { includePaths: ['src', 'node_modules'] },
				}),
				// Optimize carbon-components imports
				optimizeImports()
			],
		}),

		// Extract any component CSS into a separate file for performance
		css({ output: 'bundle.css' }),

		copy({
			targets: [
				{ src: ['index.html', 'favicon.png', 'src/syntax-highlighting.js'], dest: outputFolder},
			],
			verbose: true,
		}),

		// If you have external dependencies installed from
		// npm, you'll most likely need these plugins. In
		// some cases you'll need additional configuration -
		// consult the documentation for details:
		// https://github.com/rollup/plugins/tree/master/packages/commonjs
		resolve({
			browser: true,
			dedupe: ['svelte']
		}),

		commonjs(),

		// In dev mode, call `npm run start` once
		// the bundle has been generated
		!production && serve(),

		// Watch the `public` directory and refresh the
		// browser on changes when not in production
		!production && livereload(outputFolder),

		// If we're building for production (npm run build
		// instead of npm run dev), minify
		production && terser()
	],
	watch: {
		clearScreen: false
	}
};
