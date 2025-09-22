
/** @type { import('@storybook/react-webpack5').StorybookConfig } */
import path from "path";

const config = {
    stories: [
        "../src/**/*.mdx",
        "../src/**/*.stories.@(js|jsx|mjs|ts|tsx)"
    ],
    addons: [
        "@storybook/preset-create-react-app",
        "@storybook/addon-docs"
    ],
    framework: {
        name: "@storybook/react-webpack5",
        options: {}
    },
    staticDirs: [
        "../public"
    ],
    webpackFinal: async (config) => {
        config.resolve.alias = {
            ...config.resolve.alias,
            "@atoms": path.resolve(__dirname, "../src/components/atoms"),
            "@molecules": path.resolve(__dirname, "../src/components/molecules"),
            "@organisms": path.resolve(__dirname, "../src/components/organisms"),
            "@utils": path.resolve(__dirname, "../src/utils"),
        };
        return config;
    }
};

export default config;
