/**
 * Created by Administrator on 2015-07-04.
 */
var CommentForm = React.createClass({
	handleSubmit: function(e) {
		e.preventDefault();
		var author = React.findDOMNode(this.refs.author).value.trim();
		var text = React.findDOMNode(this.refs.text).value.trim();
		if (!text || !author) {
			return;
		}
		// TODO: 서버에 요청을 전송합니다
		React.findDOMNode(this.refs.author).value = '';
		React.findDOMNode(this.refs.text).value = '';
		return;
	},
	render: function() {
		return (
			<form className="commentForm" onSubmit={this.handleSubmit}>
				<input type="text" placeholder="이름" ref="author" />
				<input type="text" placeholder="내용을 입력하세요..." ref="text" />
				<input type="submit" value="올리기" />
			</form>
		);
	}
});
