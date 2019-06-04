import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';

import {
  SearchBox,
  RefinementListFilter,
  Hits,
  HitsStats,
  SelectedFilters,
  HierarchicalMenuFilter,
  Pagination,
  ResetFilters,
  SearchkitProvider,
  Layout,
  LayoutBody,
  SearchkitManager,
  SideBar,
  LayoutResults,
  ActionBar,
  ActionBarRow, NoHits
} from "searchkit";

class Step extends Component {
  render() {
    const searchkit = new SearchkitManager("http://demo.searchkit.co/api/movies/");

    const MovieHitsGridItem = (props)=> {
      const {bemBlocks, result} = props;
      let url = "http://www.imdb.com/title/" + result._source.imdbId;
      return (
        <div className={bemBlocks.item().mix(bemBlocks.container("item"))} data-qa="hit">
          <a href={url} target="_blank" rel="noopener noreferrer">
            <img data-qa="poster" alt="presentation" className={bemBlocks.item("poster")} src={result._source.poster} width="170" height="240"/>
            <div data-qa="title" className={bemBlocks.item("title")} dangerouslySetInnerHTML={{__html:result._source.title}}></div>
          </a>
        </div>
      )
    };

    return (
      <div className="animated fadeIn">
        <SearchkitProvider searchkit={searchkit}>
          <Layout>
            <SearchBox
              autofocus={true}
              searchOnChange={true}
              queryOptions={{"minimum_should_match":"70%"}}
              prefixQueryFields={["actors^1","type^2","languages","title^10"]}/>
            <LayoutBody>
              <SideBar>
                <HierarchicalMenuFilter
                  fields={["type.raw", "genres.raw"]}
                  title="Categories"
                  id="categories"/>
                <RefinementListFilter
                  id="actors"
                  title="Actors"
                  field="actors.raw"
                  operator="AND"
                  size={10}/>
              </SideBar>
              <LayoutResults>
                <ActionBar>

                  <ActionBarRow>
                    <HitsStats/>
                  </ActionBarRow>

                  <ActionBarRow>
                    <SelectedFilters/>
                    <ResetFilters/>
                  </ActionBarRow>

                </ActionBar>
                <div className="animated fadeIn">
                  <Hits
                    mod="sk-hits-grid"
                    hitsPerPage={20}
                    itemComponent={MovieHitsGridItem}
                    sourceFilter={["title", "poster", "imdbId"]}/>
                  <NoHits/>
                </div>
                <Pagination showNumbers={true}/>
              </LayoutResults>
            </LayoutBody>
          </Layout>
        </SearchkitProvider>
      </div>
    );
  }
}

export default withTranslation()(Step);
