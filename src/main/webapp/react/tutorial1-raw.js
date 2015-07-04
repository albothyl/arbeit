/**
 * Created by Administrator on 2015-07-04.
 */
var CommentBox = React.createClass({displayName: 'CommentBox',
	render: function() {
		return (
			React.createElement('div', {className: "commentBox"},
				"Hello, world! I am a CommentBox."
			)
		);
	}
});
React.render(
	React.createElement(CommentBox, null),
	document.getElementById('content')
);
