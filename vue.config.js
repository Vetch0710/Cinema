/* 这个新建的vue.config会将现有配置与原有配置整合 */

const path = require('path'); //引入path模块
const webpack = require('webpack')
const CompressionWebpackPlugin = require('compression-webpack-plugin')
const productionGzipExtensions = ['js', 'css']
const isProduction = process.env.NODE_ENV === 'production'

//导入配置
const {
  publicPath,
  assetsDir,
  outputDir,
  devPort,
} = require("./src/config/settings");

//path.join(__dirname)设置绝对路径
const resolve = (dir) => path.join(__dirname, dir);

const mockServer = () => {
  if (process.env.NODE_ENV === "development")
    return require("./mock/mockServer.js");
  else return "";
};

module.exports = {
  lintOnSave: false,
  runtimeCompiler: true,
  publicPath,
  assetsDir,
  outputDir,
  devServer: {
    hot: true,
    port: devPort,
    open: true,
    noInfo: false,
    overlay: {
      warnings: true,
      errors: true,
    },

    after: mockServer(),
  },
  configureWebpack: {
    resolve: {
      /*配置别名*/
      alias: {
        "@": resolve("src"),
        'assets': resolve('src/assets'),
        // 'common': resolve('src/common'),
        // 'components': resolve('src/components'),
        // 'network': resolve('src/network'),
        'views': resolve('src/views'),
      }
    },
    plugins: [
      new webpack.IgnorePlugin(/^\.\/locale$/, /moment$/),
      // 下面是下载的插件的配置
      new CompressionWebpackPlugin({
        algorithm: 'gzip',
        test: new RegExp('\\.(' + productionGzipExtensions.join('|') + ')$'),
        threshold: 10240,
        minRatio: 0.8
      }),
      new webpack.optimize.LimitChunkCountPlugin({
        maxChunks: 5,
        minChunkSize: 100
      })
    ]
  },

}
