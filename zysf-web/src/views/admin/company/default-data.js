/* eslint-disable */
import { renderInfo } from '@/libs/text'
import { getRelativeTime } from '@/libs/date'
export const initColumns = (that) => {
    return [
        {
            type: 'index',
            width: 60,
            align: 'center'
        },
        {
            title: 'id',
            key: 'id',
            minWidth:100,
            sortable: 'custom',
            align: 'center',
            render: renderInfo('id')
        },
        {
            title: '名称',
            key: 'name',
            minWidth:100,
            sortable: 'custom',
            align: 'center',
            render: renderInfo('name')
        },
        {
            title: 'CODE',
            key: 'code',
            minWidth:100,
            sortable: 'custom',
            align: 'center',
            render: renderInfo('code')
        },
        {
            title: '地址',
            key: 'address',
            minWidth:100,
            sortable: 'custom',
            align: 'center',
            render: renderInfo('address')
        },
        {
            title: '经度',
            key: 'lng',
            minWidth:100,
            sortable: 'custom',
            align: 'center',
            render: renderInfo('lng')
        },
        {
            title: '纬度',
            key: 'lat',
            minWidth:100,
            sortable: 'custom',
            align: 'center',
            render: renderInfo('lat')
        },
        {
          title: '更新时间',
          key: 'utime',
          minWidth:150,
          sortable: 'custom',
          align: 'center',
          render: (h, params) => {
            return h('span',getRelativeTime(new Date(params.row.utime)));
          }
        },
        {
          title: '创建时间',
          key: 'ctime',
          minWidth:150,
          sortable: 'custom',
          align: 'center',
          render: (h, params) => {
            return h('span',getRelativeTime(new Date(params.row.ctime)));
          }
        },
        {
            title: '操作',
            key: '操作',
            fixed:'right',
            width: 250,
            align: 'center',
            render: (h, params) => {
                return h('div', [
                    h('Button', {
                        props: {
                            type: 'success',
                            size: 'small'
                        },
                        style: {
                            marginRight: '5px'
                        },
                        on: {
                            click: () => {
                                that.show(params.index, true)
                            }
                        }
                    },
                    [
                        h('Icon', {
                            props: {
                                type: 'ios-copy',
                                size: 16
                            }
                        }),
                    '查看'
                    ]),
                    h('Button', {
                        props: {
                            type: 'primary',
                            size: 'small'
                        },
                        style: {
                            marginRight: '5px'
                        },
                        on: {
                            click: () => {
                                that.show(params.index, false)
                            }
                        }
                    },
                    [
                        h('Icon', {
                            props: {
                                type: 'ios-create',
                                size: 16
                            }
                        }),
                        '修改'
                    ]),
                    h('Button', {
                        props: {
                            type: 'warning',
                            size: 'small'
                        },
                        on: {
                            click: () => {
                                that.delete(params.index)
                            }
                        }
                    },
                    [
                        h('Icon', {
                            props: {
                                type: 'ios-trash',
                                size: 16
                            }
                        }),
                        ' 删除'
                    ])
                ]);
            }
        }
    ]
}
