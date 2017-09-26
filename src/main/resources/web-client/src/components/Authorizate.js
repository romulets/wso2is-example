import React, { Component } from 'react'
import queryString from 'query-string'

export default class Authorizate extends Component {

  constructor (props) {
    super(props)

    this.state = {
      code: '',
      consumerKey: '',
      accessToken: '',
      consumerSecret: 'ETP5GysqqkB3yDbHfBLsIsPM0uca'
    }

    this.handleChange = this.handleChange.bind(this)
  }

  componentDidMount () {
    const { code, consumerKey } = this.props

    this.setState({code, consumerKey})
    this.fetchAuthUri(code, consumerKey)
  }

  fetchAuthUri (authorizationToken, consumerKey) {

    const clientSecret  = this.state.consumerSecret
    const callbackUri   = "http://localhost:8080/wso2Example"
    const authPath      = "http://localhost:8080/wso2Example/api/authorizate"
    const query         = queryString.stringify({
          consumerKey,
          callbackUri,
          authorizationToken,
          clientSecret
    })

    const requestUri    = `${authPath}?${query}`

    fetch(requestUri)
      .then(response => response.json())
      .then(message => {
        const { accessToken } = message
        this.setState({ accessToken })
      })
      .catch(error => {
        console.error(error)
      })
  }

  handleChange (event) {
    this.setState({ consumerSecret: event.target.value })
  }

  render () {
    return (
      <div>
        <label htmlFor="consumerSecret">Consumer Secret: </label>
        <input type="password" id="consumerSecret" value={this.state.consumerSecret} onChange={this.consumerSecret} />

        <br /><br />

        <button onClick={this.fetchAuthUri}> Autorizar </button>
      </div>
    )
  }
}
