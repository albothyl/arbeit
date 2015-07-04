/**
 * Created by Administrator on 2015-07-04.
 */
var CommentBox = React.createClass({
	render: function() {
		return (
			<div className="commentBox">
				<h1>댓글</h1>
				<CommentList />
				<CommentForm />
			</div>
		);
	}
});
