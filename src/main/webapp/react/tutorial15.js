/**
 * Created by Administrator on 2015-07-04.
 */
var CommentForm = React.createClass({
	render: function() {
		return (
			<form className="commentForm">
				<input type="text" placeholder="이름" />
				<input type="text" placeholder="내용을 입력하세요..." />
				<input type="submit" value="올리기" />
			</form>
		);
	}
});
