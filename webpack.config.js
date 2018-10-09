var packageJSON = require('./package.json');
var path = require('path');
var webpack = require('webpack');
var package = require('./package.json');
var CommonsChunkPlugin = require('webpack/lib/optimize/CommonsChunkPlugin');
const UglifyJSPlugin = require('uglifyjs-webpack-plugin');

const PATHS = {
		build : path.join(__dirname, 'target', 'classes', 'META-INF', 'resources',
				'webjars', packageJSON.name)
};

module.exports = {
		// entry: './app/index.js'
		entry : {
			login : './src/main/javascript/login.js',
			mealcheck : './src/main/javascript/mealcheck.js',
			vendor : Object.keys(package.dependencies)
		},
		
		output : {
			path : PATHS.build,
			filename : '[name].bundle.js'
		},
		
		plugins : [
			new UglifyJSPlugin({
				uglifyOptions: {
//					debug: true,
//					beautify: true,
					
					mangle : false,
					compress : false,
					comments : false,
//					sourceMap : true,
					output : {
						beautify : true,
						preamble : "/* uglified */"
					},
					ecma : 6
				}
			}),
			new webpack.ProvidePlugin({
				$: "jquery",
				jQuery : "jquery"
			}),
			new CommonsChunkPlugin({
				name : "shared",
				minChunks : 2
			})
		],
		
		module : {
			rules : [ {
				test : /\.vue$/,
				loader : 'vue-loader',
				options : {
					loaders : {}
			// other vue-loader options go here
				}
			}, {
					test: /\.css$/,
					use: ['style-loader', 'css-loader']
				}, {
					test : /\.(png|woff|woff2|eot|ttf|svg)$/,
					loader: 'url-loader?limit=100000'
				}
			]
		},
		resolve : {
			alias : {
				'vue$' : 'vue/dist/vue.esm.js'
			}
		}
};