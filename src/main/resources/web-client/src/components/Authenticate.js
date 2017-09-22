import React, { Component } from 'react'
import Authorizate from './Authorizate'
import queryString from 'query-string'

export default class Authenticate extends Component {

  state = {
    alreadyAuthenticated: false,
    consumerKey: 'FsfebI97lO_bPxIZE_JT7PEDdDca',
    authUri: '',
    code: ''
  }

  componentDidMount () {
    const { code } = queryString.parse(this.props.location.search)

    if (code) {
      this.setState({code, alreadyAuthenticated: true})
    } else {
      this.fetchAuthUri()
    }
  }

  fetchAuthUri () {
    const callbackUri = "http://localhost:8080/wso2Example"
    const consumerKey = this.state.consumerKey
    const authPath    = "http://localhost:8080/wso2Example/api/authenticate"
    const requestUri  = `${authPath}?consumerKey=${consumerKey}&callbackUri=${callbackUri}`

    fetch(requestUri)
      .then(response => response.json())
      .then(message => {
        const { code, alreadyAuthenticated } = message
        if (alreadyAuthenticated) {
          this.setState({code, alreadyAuthenticated: true})
        } else {
          this.setState({ authUri: message.authPage })
        }

      })
      .catch(error => {
        console.error(error)
      })
  }

  redirectToAuthenticate (code) {
    const consumerKey = this.state.consumerKey
    const query = queryString.stringify({code, consumerKey})
    this.props.history.push("auhtorizate?" + query)
  }

  render () {
    if (this.state.alreadyAuthenticated) {
      return (
        <Authorizate code={this.state.code}
                    consumerKey={this.state.consumerKey} />
      )
    } else {
      return (
        <a href={this.state.authUri}> Fazer Login no wso2 </a>
      )
    }
  }
}
