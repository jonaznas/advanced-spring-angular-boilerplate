/**
 * Webpack config for Intellij IDEA
 * Set path in Settings > Languages & Frameworks > Javascript > Webpack
 */

module.exports = {
  resolve: {
    alias: {
      '@': path.resolve(__dirname),
      '~': path.resolve(__dirname),
    }
  }
};
