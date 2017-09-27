import React, { Component } from 'react'
import queryString from 'query-string'
import Validate from './Validate'

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
    this.fetchAuthUri = this.fetchAuthUri.bind(this)
  }

  componentDidMount () {
    const { code, consumerKey } = this.props

    this.setState({code, consumerKey})
  }

  fetchAuthUri (event) {
    const { code, consumerKey, consumerSecret } = this.state

    const callbackUri   = "http://localhost:8080/wso2Example"
    const authPath      = "http://localhost:8080/wso2Example/api/authorizate"
    const query         = queryString.stringify({
          consumerKey,
          consumerSecret,
          callbackUri,
          authorizationToken: code
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
    if (this.state.accessToken.trim().length === 0) {
      return (
        <div>
          <label htmlFor="consumerSecret">Consumer Secret: </label>
          <input type="password" id="consumerSecret" defaultValue={this.state.consumerSecret} onChange={this.handleChange} />

          <br /><br />

          <button onClick={this.fetchAuthUri}> Authorizate </button>
        </div>
      )
    } else {
      return (
        <Validate accessToken={this.state.accessToken} />
      )
    }
  }
}
