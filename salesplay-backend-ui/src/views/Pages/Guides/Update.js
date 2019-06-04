import React, { Component } from 'react';
import { connect } from 'react-redux';
import { updateGuide, findGuideById } from "../../../actions/guides-actions";
import UpdateForm from '../../../components/Guides/Form/Index'
import Page404 from "../Page404";
import Loader from "../../../components/Loading/Loader";
import Error from "../../../components/Error/Error";

const mapStateToProps = state => ({
  guide: state.guides.items,
  loading: state.guides.loading,
  error: state.guides.error
});

const mapActionsToProps  = {
  updateGuide: updateGuide,
  findGuideById: findGuideById
};

class Update extends Component {
  constructor(props) {
    super(props);
    this.onSubmit = this.onSubmit.bind(this);
  }

  onSubmit = (values, { setSubmitting, setErrors }) => {
    this.props.updateGuide(values);
    setSubmitting(false);
  };

  componentWillMount() {
    let guideId = this.props.match.params.id;
    if (guideId && !isNaN(guideId)) {
      this.props.findGuideById(guideId);
    }
  }

  render() {
    const { error, loading, guide } = this.props;

    let guideId = this.props.match.params.id;
    if (guideId) {
      if (isNaN(guideId)) {
        return <Page404 />;
      }
    }

    if (error) {
      return <Error error={error}/>
    } else if (loading) {
      return <Loader/>
    }

    return (
      <div className="animated fadeIn">
        <UpdateForm
          onSubmit={this.onSubmit}
          guide={guide}/>
      </div>
    );
  }
}

export default connect(mapStateToProps, mapActionsToProps)(Update);

