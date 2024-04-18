import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import path from 'path';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  // 절대 경로 추가
  resolve: {
    alias: [
      { find: '@', replacement: path.resolve(__dirname, 'src') },
      // { find: '@pages', replacement: path.resolve(__dirname, 'src/pages') },
    ],
  },
});
