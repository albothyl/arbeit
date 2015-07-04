/**
 * Created by Administrator on 2015-07-04.
 */
var CommentBox = React.createClass({
	getInitialState: function() {
		return {data: []};
	},
	render: function() {
		return (
			<div className="commentBox">
				<h1>댓글</h1>
				<CommentList data={this.state.data} />
				<CommentForm />
			</div>
		);
	}
});
