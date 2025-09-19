import { defineConfig } from 'vite'
import { svelte } from '@sveltejs/vite-plugin-svelte'
// Jeśli wolisz ścieżkę absolutną zamiast względnej, odkomentuj:
// import { fileURLToPath } from 'node:url'
// import { dirname, resolve } from 'node:path'
// const __filename = fileURLToPath(import.meta.url)
// const __dirname = dirname(__filename)

export default defineConfig({
    plugins: [svelte()],
    base: '/', // poprawne ścieżki assetów po zbudowaniu\
    resolve: {
        alias: {
            '@': resolve(__dirname, 'src'),                 // => import x from "@/..."
            // opcjonalnie:
            // '@components': resolve(__dirname, 'src/components'),
            // '@pages': resolve(__dirname, 'src/components/pages'),
        }
    },
    server: {
        proxy: {
            '/api/v1': {
                target: 'http://localhost:9090', // backend
                changeOrigin: true,
                secure: false,
            }
        }
    },
    build: {
        // zapisuj ZAWARTOŚĆ builda bezpośrednio do static/ backendu
        outDir: '../../../../../../../../resources/static',
        // outDir: resolve(__dirname, '../backend/src/main/resources/static'), // wariant z absolutną ścieżką
        emptyOutDir: true, // czyści static/ przed buildem
    },
})
