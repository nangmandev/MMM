module.exports = {
  env: {
    browser: true,
    es2021: true,
    node: true,
  },
  extends: [
    'standard',
    'prettier',
    'standard-with-typescript',
    'plugin:react/recommended',
  ],
  overrides: [
    {
      env: {
        node: true,
      },
      files: ['.eslintrc.{js,cjs}'],
      parserOptions: {
        sourceType: 'script',
      },
    },
  ],
  parserOptions: {
    project: '**/tsconfig.json',
    ecmaVersion: 'latest',
    sourceType: 'module',
  },
  plugins: ['react', 'prettier'],
  rules: {
    "no-unused-vars": "off",
    // 들여쓰기 깊이 제한
    'max-depth': ['error', 2],
    'prettier/prettier': ['error'],
    // 최상단 파일이 아닌 곳에서는 import react 생략 가
    'react/react-in-jsx-scope': 'off',
    // js, jsx 파일 둘다 사용 가능하게
    'react/jsx-filename-extension': [
      1,
      { extensions: ['.js', '.jsx'] },
    ],
  },
};
