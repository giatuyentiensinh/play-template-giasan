# Add template to Playframework

## file `tpl/blocks/nav.html: line 365` chèn submenu "Show collection"
``` html	
<li ui-sref-active="active">
	<a ui-sref="app.page.collection">
		<span>Show Collection</span>
	</a>
</li>
```

## file `config.router.js: line 301` cầu hình khi click vào submenu "Show collection" -> mở file tuyen_collection
``` javascript
.state('app.page.collection', {
	url: '/collection',
	templateUrl: 'tpl/tuyen_collection.html',
	resolve: {
		deps: ['$ocLazyLoad',
			function( $ocLazyLoad){
				return $ocLazyLoad.load('js/controllers/showcollection.js');
		}]
	}
})
```
thêm ng-controller="showCollection" trong file controllers/showcollection.js
    
## file tuyen_collection.html

Nội dung context submenu "Show collection"  
