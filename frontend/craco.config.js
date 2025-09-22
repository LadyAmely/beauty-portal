const path = require("path");

module.exports = {
    webpack: {
        alias: {
            "@atoms": path.resolve(__dirname, "src/components/atoms"),
            "@molecules": path.resolve(__dirname, "src/components/molecules"),
            "@organisms": path.resolve(__dirname, "src/components/organisms"),
            "@utils": path.resolve(__dirname, "src/utils"),
        },
    },
};
