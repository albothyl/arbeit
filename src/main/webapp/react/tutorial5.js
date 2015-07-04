/**
 * Created by Administrator on 2015-07-04.
 */
var CommentList = React.createClass({
	render: function() {
		return (
			<div className="commentList">
				<Comment author="Pete Hunt">댓글입니다</Comment>
				<Comment author="Jordan Walke">*또 다른* 댓글입니다</Comment>
			</div>
		);
	}
});
