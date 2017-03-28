var path = require('path');
var webpack = require('webpack');

var prod = process.env.NODE_ENV === "production";

module.exports = {
  entry: './lib/bundle.js',
  externals: [{
    /* Rely on React components imported by Reagent */
    "react": "var React",
    "react-dom": "var ReactDOM",
    "react-dom/server": "var ReactDOMServer"
  }],
  output: {
    path: "./src/vendor/",
    filename: prod ? 'bundle.min.js' : 'bundle.js'
  },
  module: {
    loaders: [
      { test: /.jsx?$/, loader: 'babel-loader', include: "./lib" }
    ],
    noParse: /slider\/dist/
  },
  plugins: [
    new webpack.DefinePlugin(
      {'process.env': { 'NODE_ENV': prod ? '"production"' : '"development"'}})
  ]
}
